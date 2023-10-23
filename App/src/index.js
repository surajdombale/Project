import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
// import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
// import Layout from "./pages/Layout";
// import Home from "./pages/Home";
// import NoMatch from './pages/NoMatch';
// import NavBar from './pages/NavBar';
// import Product from './pages/Product';
// import About from './pages/About';

// export default function App() {
//   return (
//     <BrowserRouter>
//     <NavBar/>
//       <Routes>
//         {/* <Route path="/" element={<NavBar/>}> */}
//           <Route path="/" element={<Home />} />
//           <Route path="product" element={<Product />} />
//           <Route path="about" element={<About />} />
//           <Route path="*" element={<NoMatch />} />
//         {/* </Route> */}
//       </Routes>
//     </BrowserRouter>
//   );
// }

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App/>);
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
  
//     <App />
    
//   </React.StrictMode>
// );

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
