
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Table from './features/Table';
import AddCategory from './features/AddCategory';
import EditCategory from './features/EditCategory';
import NavBar from './features/NavBar';

export default function App() {
  return (
    <div className='App'>
    
    <BrowserRouter>

    {/* <NavBar/> */}

          <Routes>
            <Route path="/" element={<NavBar/>}/>
              <Route path="/addcategory" element={<AddCategory/>} />
              <Route path="/editCategory/:id" element={<EditCategory/>}/>
              <Route path="/:id" element={<Table/>} />      
           </Routes>
        </BrowserRouter>
       </div>
  )
}

