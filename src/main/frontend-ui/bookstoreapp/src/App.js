import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";
import BookDetails from "./components/BookDetails/BookDetails";
import Cart from "./components/Cart";
import "./App.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        {/*<Cart/>*/}
        <Routes>
          <Route path="/" element={<Books />} />
          <Route path="/book/:id" element={<BookDetails />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}
export default App;
