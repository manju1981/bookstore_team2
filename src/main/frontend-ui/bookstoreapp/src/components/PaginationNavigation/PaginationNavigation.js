import React from 'react'
import { Pagination } from 'semantic-ui-react'
import previous_icon from "../../assets/previous_icon.png";
import next_icon from "../../assets/next_icon.png";

const PaginationNavigation = () => (
    <Pagination defaultActivePage={1} totalPages={5} boundaryRange={0}
                ellipsisItem={null}
                firstItem={null}
                lastItem={null}
                prevItem={undefined}
                nextItem={undefined}
                siblingRange={1}
              />
)

export default PaginationNavigation;