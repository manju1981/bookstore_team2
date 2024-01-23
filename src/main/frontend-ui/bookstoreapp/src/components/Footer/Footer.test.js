import { render, screen } from "@testing-library/react";
import Footer from "./Footer";

describe("Footer Container", () => {
  it("should render the footer", () => {
    render(<Footer />);
    expect(screen.getByTestId("Footer-test")).toBeInTheDocument();
  });
});
