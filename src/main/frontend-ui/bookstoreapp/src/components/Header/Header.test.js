import {getByTestId, render} from "@testing-library/react";
import Header from "./Header";

describe("Header Container ", () => {
    beforeEach(() => {
    });
    afterEach(() => {
        jest.clearAllMocks();
    });
    it("should render the header", () => {
        const {getByTestId} = render(<Header/>);
        expect(getByTestId("Header-test")).toBeInTheDocument();
    });
    it("should render the logo & title under header, ", () => {
        const {getByTestId} = render(<Header/>);
        expect(getByTestId("Header-test")).toBeInTheDocument();
        expect(getByTestId("Logo-test")).toBeInTheDocument();
        expect(getByTestId("Title-test")).toBeInTheDocument();
    });
});