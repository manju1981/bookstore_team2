import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
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

  it("should handle result selection and navigate to book page", async () => {
    render(
      <MemoryRouter>
        <Search />
      </MemoryRouter>
    );

    const mockFetch = jest.spyOn(global, "fetch");
    mockFetch.mockResolvedValueOnce({
      json: async () => ({ content: [{ id: 1, title: "Title of the Book" }] }),
    });

    const mockNavigate = jest.fn();
    jest.mock("react-router-dom", () => ({
      ...jest.requireActual("react-router-dom"),
      useNavigate: () => mockNavigate,
    }));

    const input = screen.getByPlaceholderText("Search books...");
    userEvent.type(input, "test");

    await waitFor(() => {
      expect(screen.getByTestId("Search-container")).not.toHaveClass("loading");
    });

    const resultItem = await screen.findByText("Title of the Book", {}, { timeout: 5000 });
    const id = resultItem.parentElement.id;

    waitFor(() => {
      expect(mockNavigate).toHaveBeenCalledTimes(1);
    });

    mockFetch.mockRestore();
  });
});
