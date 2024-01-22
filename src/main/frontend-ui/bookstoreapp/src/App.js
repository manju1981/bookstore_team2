// import logo from './logo.svg';
import "./App.css";
import Books from "./components/Books/Books";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";

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
