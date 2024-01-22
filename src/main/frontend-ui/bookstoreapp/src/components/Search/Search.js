import React from "react";
import { Search as SemanticSearch } from "semantic-ui-react";

const Search = () => {
  const initialState = {
    loading: false,
    results: [],
    value: '',
  };

  const exampleReducer = (state, action) => {
    switch (action.type) {
      case 'CLEAN_QUERY':
        return initialState;
      case 'START_SEARCH':
        return { ...state, loading: true, value: action.query };
      case 'FINISH_SEARCH':
        return { ...state, loading: false, results: action.results };
      case 'UPDATE_SELECTION':
        return { ...state, value: action.selection };
      default:
        throw new Error();
    }
  };

  const [state, dispatch] = React.useReducer(exampleReducer, initialState);
  const { loading, results, value } = state;

  const timeoutRef = React.useRef();

  const handleSearchChange = React.useCallback((e, data) => {
    clearTimeout(timeoutRef.current);
    dispatch({ type: 'START_SEARCH', query: data.value });

    timeoutRef.current = setTimeout(() => {
      if (data.value.length === 0) {
        dispatch({ type: 'CLEAN_QUERY' });
        return;
      }

      const re = new RegExp(RegExp.escape(data.value), 'i');
      const isMatch = (result) => re.test(result.title);

      // Define 'source' variable (dummy data for example)
      const source = Array.from({ length: 5 }, (_, index) => ({
        title: `Result ${index + 1}`,
        description: `Description for result ${index + 1}`,
        // Add other properties as needed
      }));

      dispatch({
        type: 'FINISH_SEARCH',
        results: source.filter(isMatch),
      });
    }, 300);
  }, []);

  React.useEffect(() => {
    return () => {
      clearTimeout(timeoutRef.current);
    };
  }, []);

  return (
    <SemanticSearch
      loading={loading}
      placeholder='Search books...'
      onResultSelect={(e, data) =>
        dispatch({ type: 'UPDATE_SELECTION', selection: data.result.title })
      }
      onSearchChange={handleSearchChange}
      results={results}
      value={value}
    />
  );
};

export default Search;
