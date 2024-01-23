import { render } from "@testing-library/react";
import Footer from "./Footer";

describe("Footer Container", () => {
  it("should render the footer", () => {
    const { getByTestId } = render(<Footer />);
    expect(getByTestId("Footer-test")).toBeInTheDocument();
  });
});
