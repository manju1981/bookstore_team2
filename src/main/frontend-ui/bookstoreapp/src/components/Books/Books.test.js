import { render, screen } from "@testing-library/react";
import Books from "./Books";

describe("Books Container", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });
  it("should render the list of books", () => {
    render(<Books />);
    expect(screen.getByTestId("Books-test")).toBeInTheDocument();
  });
});
