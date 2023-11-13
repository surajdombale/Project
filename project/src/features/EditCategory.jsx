  import React, { useState,useEffect } from 'react'
  import './AddCategory.css';
  import axios from 'axios';
  import EditData from './EditData';
  import { useParams } from 'react-router';
  import { useNavigate } from 'react-router';

  const EditCategory = ()  => {
    const [parent,setParent]=useState([]);
    const [parentName,setParentName]=useState('');
    const [select,setSelect]=useState('');
    const [selectedFile, setSelectedFile] = useState(null);
    const [previewImage, setPreviewImage] = useState('');
    const [name,setName]=useState('');
    const [aliasName,setAliasName]=useState('');
    const [isChecked, setIsChecked] = useState('');
    // get category id from query
    const { id } = useParams();
    const navigate = useNavigate();

  

  //// function used to convert image url into photo
    // async function imageUrlToFile(imageUrl, fileName) {
    //   try {
    //     // Fetch the image data from the URL
    //     const response = await fetch(imageUrl);
    //     const blob = await response.blob();
    
    //     // Create a File object with the provided file name (you can customize this)
    //     const file = new File([blob], fileName, { type: response.headers.get('content-type') });
    
    //     return file;
    //   } catch (error) {
    //     console.error('Error converting image URL to file:', error);
    //     return null;
    //   }
    // }



  // used to get data from server with the help of id
    const getCategoryById=async()=>{
    setPreviewImage(`http://localhost:8080/ShopmeAdmin/category/download/${id}`);
    try{console.log('i am here')
      const respons=  await axios.get(`http://localhost:8080/ShopmeAdmin/category/getsub/${id}`);
      console.log(respons.data);
      let enabled='false';
    if(respons.data.enabled==true){
      enabled='true';
    }
      
    //  imageUrlToFile(previewImage, respons.data.name)
    // .then((file) => {
    //   if (file) {
    //     // You now have the 'file' that you can use as needed.
    //     setSelectedFile(file)
    //   } else {
    //     console.log('Image URL could not be converted to a file.');
    //   }
    // });
      setParentName(respons.data.parentName)
      setName(respons.data.name)
      setIsChecked(enabled)
      setAliasName(respons.data.alias)
      }
      catch(error){
        console.log(error); 
        console.error("erroe fetching data", error)
      }
  }

      // used to handle enabled and desabled 
      const handleCheckboxChange = (event) => {
        setIsChecked(event.target.checked);
      };

  // used to handle the submit 

      const handleSubmitCall= ()=>{
      
      EditData(id,isChecked,aliasName ,name ,parentName ,selectedFile)
        .then((result)=>{
          if(result){
            console.log(result);
            navigate(`/Id no: ${id} is Edited`);
          }
        });
        
      }



      // SHow preview of uploaded image
      const handleFileChange = (event) => {
      const file = event.target.files[0];
      setSelectedFile(file);
      if (file) {
        // Display a preview of the selected image
        const reader = new FileReader();
        reader.onload = (e) => {
          setPreviewImage(e.target.result);
        };
        reader.readAsDataURL(file);
        setSelectedFile(file);
      }
      };




      const handleSelectChange = (event) => {
      const newValue = event.target.value;
      setSelect(newValue);
      setParentName(newValue);
      };




      
      useEffect(()=>{
      const newCategoryInput = document.getElementById('new-category');
      console.log(select)
      if (select === 'create-new') {
        newCategoryInput.style.display = 'block';
        newCategoryInput.required = true;
        } else {
        newCategoryInput.style.display = 'none';
        newCategoryInput.required = false;
      }
      },[select],[])
      
    //used to get category data
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
      useEffect(()=>{
        axiosParentData();
        getCategoryById();
      
      },[])
      
    return (
      <body1>
      
      <div className="container">
          <h1>Edit SubCategory</h1>
          <form id="subcategory-form"  encytpe="ENCTYPE_HERE">
              <div><label for="subcatgory-name">Subcatgory Name:</label>
              <input type="text" id="subcatgory-name" value={name} onChange={(e)=>setName(e.currentTarget.value)} name="name" required /></div>
              <div><label for="subcatgory-aliasname">Subcatgory Alias Name:</label>
              <input type="text" id="subcatgory-aliasname" value={aliasName} onChange={(e)=>setAliasName(e.currentTarget.value)}  name="alias" required />
              </div><br/>
              <div>
              <label for="parent">Choose Parent:</label>
              <select name="parentName"  onChange={handleSelectChange}   id="parent" required>
              <option value="none" selected disabled hidden>Select an Option</option>
            { parent.map((item,index,key)=>(<option  value={item.name} >{item.name}</option>))}
            <option value="create-new" onChange={(e)=>setParentName(select) }>Create New Category</option>
          </select>
          </div>
          <div>
          <input type="text" id="new-category" value={parentName} name= 'parentName' class="new-category-input"  onChange={(e)=>setParentName(e.currentTarget.value) } placeholder="Enter new" required/>
          <label for="subcatgory-enabled">SubCatgory Enabled</label> 
          <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange} required/>
            </div>
            <div>
              <label for="subcatgory-image">SubCatgory Image:</label>
              <input type="file" id="catgory-image" name="catgory-image"  accept="image/*" onChange={(e)=>handleFileChange(e)} /></div>
              <div id="image-preview">
              {previewImage && (
              <img id="preview" src={previewImage} alt='subcatgory'/>
              )}
              
          </div>
          <button type="button" onClick={handleSubmitCall} required>Save</button>
          </form>
          <a href='/welcome' ><button >Cancel</button></a>
          </div>
          </body1>

    )
  }

  export default EditCategory;
