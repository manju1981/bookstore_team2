import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

test("renders bookstore application", () => {
  render(<App />);

expect(screen.getByTestId("Header-test")).toBeInTheDocument();
expect(screen.getByTestId("Books-test")).toBeInTheDocument();
expect(screen.getByTestId("Footer-test")).toBeInTheDocument();
});
