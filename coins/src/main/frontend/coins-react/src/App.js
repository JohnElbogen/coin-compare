import logo from './Bitcoin Logo.svg';
import logo2 from './Ethereum Logo.svg';
import './App.css';
import FetchPrice from "./components/FetchPrice";

function App() {

  return (
    <div className="row">
        <div className="divCol1">
          <header className="col1">
            <img src={logo} className="logo" alt="logo" />
            <p className="coin">Bitcoin</p>
            <div className="prices"><FetchPrice currency={"BTC"} /></div>
          </header>
        </div>
        <div className="divCol2">
          <header className="col2">
            <img src={logo2} className="logo" alt="logo" />
            <p className="coin">Ethereum</p>
            <div className="prices"><FetchPrice currency={"ETH"} /></div>
          </header>
        </div>
    </div>
  );
}

export default App;
