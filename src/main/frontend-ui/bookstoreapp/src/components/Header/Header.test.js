import { render, screen } from "@testing-library/react";
import Header from "./Header";

describe("Header Container", () => {
  it("should render the header", () => {
    render(<Header />);
    expect(screen.getByTestId("Header-test")).toBeInTheDocument();
  });

  it("should render the logo & title under header", () => {
    render(<Header />);
    expect(screen.getByTestId("Header-test")).toBeInTheDocument();
    expect(screen.getByTestId("Logo-test")).toBeInTheDocument();
    expect(screen.getByTestId("Title-test")).toBeInTheDocument();
  });
});
