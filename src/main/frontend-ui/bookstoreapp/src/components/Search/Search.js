import React from "react";

const Search = () => {
  return (
    <div style={{ width: "100%" }}>
      <form className="search-container">
        <input
          type="text"
          name="text"
          placeholder="search users..."
          className="search-input"
        />
        <button type="submit" className="button">
          Search
        </button>
      </form>
    </div>
  );
};

export default Search;
