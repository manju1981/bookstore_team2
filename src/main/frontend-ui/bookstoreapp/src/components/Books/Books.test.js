import { getByTestId, render } from "@testing-library/react";
import Books from "./Books";

describe("Books Container ", () => {
  beforeEach(() => {});
  afterEach(() => {
    jest.clearAllMocks();
  });
  it("should render the list of books", () => {
    const { getByTestId } = render(<Books />);
    expect(getByTestId("Books-test")).toBeInTheDocument();
  });
});
