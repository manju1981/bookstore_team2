import React from "react";
import "./App.css";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Books from "./components/Books/Books";

function App() {
  return (
    <div className="App">
      <Header />
        <Books />
      <Footer />
    </div>
  );
}

export default App;