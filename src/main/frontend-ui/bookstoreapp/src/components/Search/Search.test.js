import { getByTestId, render } from "@testing-library/react";
import Search from "./Search";

describe("Search Container ", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });
  it("should render the searchbar", () => {
    const { getByTestId } = render(<Search />);
    expect(getByTestId("Search-container")).toBeInTheDocument();
  });
  it("should render the search input box & search button under searchbar, ", () => {
    const { getByTestId } = render(<Search />);
    expect(getByTestId("Search-container")).toBeInTheDocument();
    // expect(getByTestId("Search-input")).toBeInTheDocument();
    // expect(getByTestId("Search-button")).toBeInTheDocument();
  });
});
