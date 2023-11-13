import React from 'react'
import axios from 'axios';
import { useEffect, useState } from 'react';

import CSV from './CSV';
import { useNavigate, useParams } from 'react-router';


const Table = () => {
    const [axi,setaxiJson]=useState([]);
    const [search,setSearch]=useState('');
    const [filters, setFilters] = useState({
      category: 'All',
    });
    const [parent,setParent]=useState([]);
    const [isVisible, setIsVisible] = useState(false);
    const [message, setMessage] = useState('Welcome');
    const { id } = useParams();
    const navigate = useNavigate();
    const [axiFilter,setAxiFilter]=useState([]);
    

// used to show message and clear it after 5 seconds
    useEffect(() => {
      setIsVisible(true);
      const timeout = setTimeout(() => {
        setIsVisible(false);
       navigate('/Welcome')
      }, 5000); // 5 seconds
      return () => clearTimeout(timeout);
      
    }, [message]);
  

    //Function used to Filter the Category based on their parent
    
    const handleFilterChange = (event) => {
      const { name, value } = event.target;
      setFilters({ ...filters, [name]: value });
      // setAxiFilter(axi.filter((item) => search === '' ||  item.name.toLowerCase().includes(search.toLowerCase())).filter((item) => filters.category === 'All' || item.parentName === filters.category));
    };


       // used to get the data when page load
      useEffect(()=>{
        axiosJsonData();
        axiosParentData();
        setMessage(id);
        
      },[])


/// used to show image  
      const imageShow=(id)=>{
        return `http://localhost:8080/ShopmeAdmin/category/download/${id}`; 
      }


      //used to get categoryParent data
      
      const axiosParentData=async()=>{
       try{
          const respons=  await axios.get("http://localhost:8080/ShopmeAdmin/category");
          setParent(respons.data);
          console.log(respons.data);
          }
          catch(error){
           console.log(error); 
          console.error("erroe fetching data", error)
          }
          }
        



      //Used to enable and desable the category
      
      const handleEnable=async(id)=>{
        const rest= await axios.post(`http://localhost:8080/ShopmeAdmin/category/enabled/${id}`)
        console.log(rest)
        let enable='desabled';
        if(rest.data){
          enable='enabled'
        }
        setMessage(`Id no ${id} is ${enable}`);
        axiosJsonData();
      }


      const enabledDesabled=(a)=>{
        let result;
         if(a===true){
           result = "Enabled"
           }
            else{
                 result="Desabled"
                }
                return result;
                 }
       



      // Used to Delete Category with pop-up Confirmation
      
      const onDeleteHandler=async(id)=>{
      let confirmInput= window.confirm(`Do you want to Delete Subcategory with Id : ${id}`);
      if(confirmInput===true){
      const rest= await axios.delete(`http://localhost:8080/ShopmeAdmin/category/${id}`)
      setMessage('Category id '+id+' deleted permanantly');
      console.log(rest);
      }
      
      axiosJsonData();
      console.log(id);
      }


// used to filter the exporting data
useEffect(()=>{
  setAxiFilter(axi.filter((item) => search === '' ||  item.name.toLowerCase().includes(search.toLowerCase())).filter((item) => filters.category === 'All' || item.parentName === filters.category));
},[search])
useEffect(()=>{
  setAxiFilter(axi.filter((item) => search === '' ||  item.name.toLowerCase().includes(search.toLowerCase())).filter((item) => filters.category === 'All' || item.parentName === filters.category));
},[filters])





      // Used to fetch category data to show on table
     
      const axiosJsonData=async()=>{
      try{
      const respons=  await axios.get("http://localhost:8080/ShopmeAdmin/category/getsub");
      setaxiJson(respons.data);
      setAxiFilter(respons.data)
      console.log(respons.data);
    
      }
      catch(error){
        console.log(error); 
        console.error("erroe fetching data", error)
      }
      }


    return (
      
        <div className='cust'>
        <h1>Manage Categories</h1>
        <div><a href='/addcategory'><button >Add</button></a> 
        <button  onClick={()=>CSV(axiFilter)}>CSV</button> 
        </div>
        <div className= 'filteroption'><lable>Filter:</lable>
        <select name="category" onChange={handleFilterChange}>
          <option value="All">All</option>
          { parent.map((item,index,key)=>(<option  value={item.name} >{item.name}</option>))}
        </select><br/>
         <lable>Search:</lable><input type='text' className='iptext' onChange={(e)=>setSearch(e.currentTarget.value)}></input> </div>
         <div className={`message-bar ${isVisible ? 'show' : 'hide'}`}>
      {message}
    </div>
        <h1> Category  </h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Category Image</th>
                    <th>Category Name</th>
                    <th>Alias</th>
                    <th>Parent</th>
                    <th>Enabled</th>
                    <th>Action</th>
                </tr>
        {axi.filter((item) => search === '' ||  item.name.toLowerCase().includes(search.toLowerCase())).filter((item) => filters.category === 'All' || item.parentName === filters.category).map((item,index,key)=>(
          
            <tr>
            <td>{item.id}</td>
            <td><img src={imageShow(item.id)} alt={item.name} height="130" width="130"/></td>
            <td>{item.name}</td>
            <td>{item.alias}</td>
            <td>{item.parentName}</td>
            <td><button type="radio" className={item.name} onClick={()=>handleEnable(item.id)}>{enabledDesabled(item.enabled)}</button></td>
            <td>
              <a href={`/editcategory/${item.id}`}>
              <button>Edit</button>
              </a>
              <button type="button" onClick={()=>onDeleteHandler(item.id)}>Delete</button>
              </td>
                      </tr> 
            
         ))
        }
    </table>
    
    </div>
    )
}

export default Table
