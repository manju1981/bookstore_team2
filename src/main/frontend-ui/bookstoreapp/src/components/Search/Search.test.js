import { render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom"; 
import Search from "./Search";

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"), 
  useNavigate: jest.fn(),
}));

describe("Search Container", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });

  it("should render the searchbar", () => {
    render(
      <MemoryRouter>
        <Search />
      </MemoryRouter>
    );
    expect(screen.getByTestId("Search-container")).toBeInTheDocument();
  });

  it("should render the search input box & search button under searchbar", () => {
    render(
      <MemoryRouter>
        <Search />
      </MemoryRouter>
    );
  });
});
