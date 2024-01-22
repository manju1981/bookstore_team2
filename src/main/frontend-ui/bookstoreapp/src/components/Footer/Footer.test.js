import {getByTestId, render} from "@testing-library/react";
import Footer from "./Footer";
import {act} from "react-dom/test-utils";
import {click} from "@testing-library/user-event/dist/click";

describe("Footer Container ", () => {
    beforeEach(() => {
    });
    afterEach(() => {
        jest.clearAllMocks();
    });
    it("should render the footer", () => {
        const {getByTestId} = render(<Footer/>);
        expect(getByTestId("Footer-test")).toBeInTheDocument();
    });
    it("should render the left navigation under footer, ", () => {
        const {getByTestId, queryByTestId} = render(<Footer/>);
        expect(getByTestId("Footer-test")).toBeInTheDocument();
        expect(getByTestId("Left-navigation")).toBeInTheDocument();
        // act(() = {
        //     fireEvent.click(getByTestId("Left-navigation"));
        // });
        // expect(queryByTestId("Call-to-action")).toBeCalled();
    });
    it('should  render the right navigation under footer', () => {
        const {getByTestId} = render(<Footer/>);
        expect(getByTestId("Footer-test")).toBeInTheDocument();
        expect(getByTestId("Right-navigation")).toBeInTheDocument();
        // act(() = {
        //     fireEvent.click(getByTestId("Right-navigation"));
        // });
        // expect(queryByTestId("Call-to-action")).toBeCalled();
    });
});