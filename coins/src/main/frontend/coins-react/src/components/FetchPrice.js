import React from "react";

export default class MyComponent extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      prices: []
    };
  }

  //GET request for our backend
  //returns updated json data for bitcoin and eth prices
  componentDidMount() {
    fetch("http://ec2-18-223-32-238.us-east-2.compute.amazonaws.com:8080/api")
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            prices: result
          });
          console.log(result)
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    const currency = this.props.currency;
    const { error, isLoaded, prices } = this.state;

    //This logic is used to determine the reccomended exchange to buy
    //or sell on
    let recBuyBit, recSellBit, recBuyEth, recSellEth;
    if (this.state.prices.binanceBTCBuy <= this.state.prices.geminiBTCBuy){
      recBuyBit = "Binance";
    } else {
      recBuyBit = "Gemini";
    }
    if (this.state.prices.binanceBTCSell >= this.state.prices.geminiBTCSell){
      recSellBit = "Binance";
    } else {
      recSellBit = "Gemini";
    }

    if (this.state.prices.binanceETHBuy <= this.state.prices.geminiETHBuy){
      recBuyEth = "Binance";
    } else {
      recBuyEth = "Gemini";
    }
    if (this.state.prices.binanceETHSell >= this.state.prices.geminiETHSell){
      recSellEth = "Binance";
    } else {
      recSellEth = "Gemini";
    }

    //this logic renders the bitcoin or ethereum table data
    //shows loading before data is fetched
    //returns error if fetch did not return appropriate data
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      if (this.props.currency == "BTC"){
        return(
            <div>
                <table className="table">
                    <tr>
                        <td>Exchange</td>
                        <th>Binance</th>
                        <th>Gemini</th>
                    </tr>
                    <tr>
                        <th>Buy Price</th>
                        <td>${this.state.prices.binanceBTCBuy}</td>
                        <td>${this.state.prices.geminiBTCBuy}</td>
                    </tr>
                    <tr>
                        <th>Sell Price</th>
                        <td>${this.state.prices.binanceBTCSell}</td>
                        <td>${this.state.prices.geminiBTCSell}</td>
                    </tr>
                </table>
                <p className="p">
                    Recommendation:
                    <br />Purchase on {recBuyBit}.
                    <br />Sell on {recSellBit}.
                </p>
            </div>
        );
      } else if (this.props.currency == "ETH"){
        return(
            <div>
                <table className="table">
                    <tr>
                        <td>Exchange</td>
                        <th>Binance</th>
                        <th>Gemini</th>
                    </tr>
                    <tr>
                        <th>Buy Price</th>
                        <td>${this.state.prices.binanceETHBuy}</td>
                        <td>${this.state.prices.geminiETHBuy}</td>
                    </tr>
                    <tr>
                        <th>Sell Price</th>
                        <td>${this.state.prices.binanceETHSell}</td>
                        <td>${this.state.prices.geminiETHSell}</td>
                    </tr>
                </table>
                <p className="p">
                    Recommendation:
                    <br />Purchase on {recBuyEth}.
                    <br />Sell on {recSellEth}.
                </p>
            </div>
        );
      } else {
        return(
            <div>Error: {error.message}</div>
        );
      }
    }
  }
}