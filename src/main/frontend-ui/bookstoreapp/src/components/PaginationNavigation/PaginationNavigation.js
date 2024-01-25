import React from "react";
import { Pagination } from "semantic-ui-react";

const PaginationNavigation = ({ currentPage, totalPages, onPageChange }) => {
  return (
    <Pagination
      activePage={currentPage}
      totalPages={totalPages}
      onPageChange={onPageChange}
      boundaryRange={0}
      ellipsisItem={null}
      firstItem={null}
      lastItem={null}
      prevItem={undefined}
      nextItem={undefined}
      siblingRange={1}
    />
  );
};
export default PaginationNavigation;
