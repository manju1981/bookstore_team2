import React, {useEffect} from 'react'
import { Pagination } from 'semantic-ui-react'
import previous_icon from "../../assets/previous_icon.png";
import next_icon from "../../assets/next_icon.png";
import {useDispatch, useSelector} from "react-redux";
import {setCurrentPage, setTotalPages} from "./PaginationAction";

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