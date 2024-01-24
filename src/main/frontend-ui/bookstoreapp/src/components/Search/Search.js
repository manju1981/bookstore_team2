import React, { useEffect, useState } from "react";
import { Search as SemanticSearch, Image } from "semantic-ui-react";
import { useNavigate } from "react-router-dom";
import _ from "lodash";

const Search = () => {
  const [books, setBooks] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchResults, setSearchResults] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const navigate = useNavigate();

  const handleSearchChange = (e, { value }) => {
    setSearchQuery(value);
    setIsLoading(true);

    // Use lodash debounce to delay the execution of the fetch request
    const debouncedSearch = _.debounce(() => {
      if (value.length === 0) {
        setSearchResults([]);
        setIsLoading(false);
        return;
      }

      fetch(`http://localhost:8090/api/v1/books/fetch-all?search=${value}`)
          .then((response) => response.json())
          .then((data) => {
            setBooks(data?.content);
            setSearchResults(data?.content);
            setIsLoading(false);
          })
          .catch((error) => {
            console.error(error);
            setIsLoading(false);
          });
    }, 300);

    debouncedSearch();

    return () => {
      // Cleanup function to clear the debounce timer
      debouncedSearch.cancel();
    };
  };

  const handleResultSelect = (e, { result }) => {
    setSearchQuery(result.title);
    console.log(result)
    navigate(`/book/${result.id}`);
  };

  return (
      <SemanticSearch
          data-testid="Search-container"
          loading={isLoading}
          placeholder="Search books..."
          onResultSelect={handleResultSelect}
          onSearchChange={handleSearchChange}
          results={searchResults.map((book) => ({
            title: book.title,
            img: book.img,
            price: book.price,
            author:book.author,
            id: book.id
          }))}
          resultRenderer={({ title, img, price,author }) => (
              <div>
                <Image src={img} size="Medium" spaced="right" />
                <div>{title}</div>
                <label className="card-author">{author}</label>
                <div>{`Price: ${price}`}</div>
              </div>
          )}
          value={searchQuery}
      />
  );
};

export default Search;
