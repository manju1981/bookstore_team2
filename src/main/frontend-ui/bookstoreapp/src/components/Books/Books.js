import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { HeaderContent, Dropdown, Header, Icon } from "semantic-ui-react";
import Card from "../Cards";
import PaginationNavigation from "../PaginationNavigation/PaginationNavigation";
import styles from "./Books.style";

const sortOptions = [
  {
    key: "title_a_to_z",
    text: "Title A to Z",
    value: "title_a_to_z",
    content: "Title A to Z"
  },
  {
    key: "title_z_to_a",
    text: "Title Z to A",
    value: "title_z_to_a",
    content: "Title Z to A"
  },
  {
    key: "price_low_to_high",
    text: "Price Low to High",
    value: "price_low_to_high",
    content: "Price Low to High"
  },
  {
    key: "price_high_to_low",
    text: "Price High to Low",
    value: "price_high_to_low",
    content: "Price High to Low"
  },
  {
    key: "rating_low_to_high",
    text: "Rating Low to High",
    value: "rating_low_to_high",
    content: "Rating Low to High"
  },
  {
    key: "rating_high_to_low",
    text: "Rating High to Low",
    value: "rating_high_to_low",
    content: "Rating High to Low"
  }
];

const Books = () => {
  const [books, setBooks] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [sortCriterion, setSortCriterion] = useState("title");
  const [sortDirection, setSortDirection] = useState("false");

  useEffect(() => {
    fetch(`http://localhost:8090/api/v1/books/fetch-all?page=${currentPage}&size=9&sort=${sortCriterion}&descending=${sortDirection}`)
      .then((response) => response.json())
      .then((data) => {
        setBooks(data?.content);
        setCurrentPage(data?.currentPage);
        setTotalPages(data?.totalPages);
      })
      .catch((error) => console.error(error));
  }, [currentPage, sortCriterion, sortDirection]);

  const handlePageChange = (e, { activePage }) => {
    setCurrentPage(activePage);
  };

  const handleSort = (_, data) => {
    switch (data.value) {
      case "title_a_to_z":
        setSortCriterion("title");
        setSortDirection("false");
        break;
      case "title_z_to_a":
        setSortCriterion("title");
        setSortDirection("true");
        break;
      case "price_low_to_high":
        setSortCriterion("price");
        setSortDirection("false");
        break;
      case "price_high_to_low":
        setSortCriterion("price");
        setSortDirection("true");
        break;
      case "rate_low_to_high":
        setSortCriterion("rating");
        setSortDirection("false");
        break;
      case "rate_high_to_low":
        setSortCriterion("rating");
        setSortDirection("true");
        break;
      default:
        break;
    }
  };

  return (
    <div className="body-container">
      <div style={styles.sortContainer}>
        <Header as="h4" floated="right">
          <Icon name="sort amount down" />
          <HeaderContent className="sort-by-label">
            Sort by{" "}
            <Dropdown
              inline
              options={sortOptions}
              defaultValue={sortOptions[0].value}
              onChange={handleSort}
            />
          </HeaderContent>
        </Header>
      </div>
      <div className="cord-collection-wrapper" data-testid="Books-test">
        {books.map((item, index) =>
          <Link to={`/book/${item.id}`} key={index}>
            <Card key={index} data={item} />
          </Link>
        )}
      </div>
      <PaginationNavigation
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
};
export default Books;
