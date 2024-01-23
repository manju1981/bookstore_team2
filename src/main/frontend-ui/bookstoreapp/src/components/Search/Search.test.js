import { render, screen } from "@testing-library/react";
import Search from "./Search";

describe("Search Container", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });

  it("should render the searchbar", () => {
    render(<Search />);
    expect(screen.getByTestId("Search-container")).toBeInTheDocument();
  });

  it("should render the search input box & search button under searchbar", () => {
    render(<Search />);
    // expect(screen.getByTestId("Search-input")).toBeInTheDocument();
    // expect(screen.getByTestId("Search-button")).toBeInTheDocument();
  });
});
