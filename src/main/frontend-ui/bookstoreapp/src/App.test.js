import { render } from "@testing-library/react";
import App from "./App";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import Footer from "./components/Footer/Footer";

test("renders bookstore application", () => {
  render(<App />);
  render(<Header />);
  render(<Books />);
  render(<Footer />);

  // const linkElement = screen.getByText(/learn react/i);
  // expect(linkElement).toBeInTheDocument();
});
