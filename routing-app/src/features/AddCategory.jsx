    import React, { useState,useEffect } from 'react'
    import './AddCategory.css';
    import axios from 'axios';
    import PostData from './PostData';
    import { useNavigate } from 'react-router';

    const AddCategory = ()  => { 
      const [parent,setParent]=useState([]);
      const [parentName,setParentName]=useState('');
      const [select,setSelect]=useState('');
      const [selectedFile, setSelectedFile] = useState(null);
      const [previewImage, setPreviewImage] = useState("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAHoA9AMBIgACEQEDEQH/xAAaAAEAAwEBAQAAAAAAAAAAAAAAAQIDBAUH/8QALxABAQABAwIDBQcFAAAAAAAAAAECAxESBCEUMXEFQVFSYRMVMlNUodEkM0JDgf/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD6IAAAAAAABtubJgCNjisnYFeJxWTsCnE4tNjYGfFHFrsjYFOKOK4CnE2WAVAAAAAAAAAAAAAAAAAoJiURYBaQkWkBGydlpitICnE4tdjiDLiixrxRxBlYrY1sVs7AzsQvYrQVQm+aAAAAAAAAAAAAAAAChQTF5FcV4C0i8iJF8ZuCZFsY20un1NT8ON2+N7R1Y9Lp6c31tSekBxTC3ym/o6NPotTLvltjPq38Rp6c20cJ6+THU1s9T8WV2+E7AnqOlww0OWnbbL3risej0u2WGelfKxyamHHKy+4HPYpY2sUsBjYpk1sZZAohNQAAAAAAAAAAAAAAAUKC2LWMsWsBfF6nR4aWPT/azTmWcnd5ePk7/Zme2eWnb2ynkDo0Opy1taTbjjtey2p1GOOdxunvZdt2PTYfZ9ZcPhvsZ8fFXl5cu4NvE4/lniMfyzqZhMZZJy+jm+gOjxOM8tNHi8d/7aupoZYzHaW2+bnvn/0G3tGTfTsm28cGT0PaP+v0cGQMsmWTXJlkDKhQAAAAAAAAAAAAAAAoUFsWkZ4rwGsrXRz4ZzKf492Eq8oPa2mWvp6uPlZXJr3+oz9W3s3V56Nwt743t6MdbHLxGW+nnljv7p5gvpaWepjcptJ7vq26bRstyzm1nkrOpzxkk6bOTbtO/wDCZ1Wf6fP9/wCAdXued1WPDXvbte8b+Lz/AE+f7/ww6jPPWuN+xzxs+lBb2le+n6ODK7u32p2ul6V59oIyZZL1nkCiEoAAAAAAAAAAAAAAAKAJi8UiwLxeVlutKDp6fqMtDK5Y7XftZXVPaOr8uDzpVpQeh94any4H3hq/Lh+7g5HIHf8AeGr8uKL7R1flwcNyVtB0dT1OWvcbnJOPwc9qLVbQLVKm1WgioTUAAAAAAAAAAAAAAAAAmJRAFkxUBeVO6gDTdG6idwW3RujdG4LWoqtoAUQAgAAAAAAAAAAAAAAAAAEoASIATuboATuboATuboATuIASIAAAAAAAAAAAf//Z");
      const [name,setName]=useState('');
      const [aliasName,setAliasName]=useState('');
      const [isChecked, setIsChecked] = useState(false);
      const navigate = useNavigate();
      const[idd,setId]=useState(0);
      

        // used to handle enabled and desabled 
        const handleCheckboxChange = (event) => {
          setIsChecked(event.target.checked);
        };



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

    // function used to handel the submit
      const handleSubmitCall=async()=>{ 
      PostData(isChecked,aliasName ,name ,parentName ,selectedFile)
        .then((rest)=>{
          if(rest!=0){
            navigate(`/id no : ${rest}  added succesfuly`);
          }
        });
      }
      












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
    const respons=  await axios.get("http://localhost:8080/category");
    setParent(respons.data);
    console.log(respons.data);
        }
        catch(error){
          console.log(error); 
          console.error("erroe fetching data", error)
        }
        }

        // useEffect(()=>{
        //   if(isChecked=='true'){
        //     setEnable(true);
        //   }
        // },[isChecked])


        useEffect(()=>{
          axiosParentData();
        },[])
        // PostData(isChecked,aliasName ,name ,parentName ,selectedFile,handleSubmit);
      
      return (
        <body1>
        
        <div className="container">
            <h1>Add New SubCategory</h1>
            <form id="subcategory-form" encytpe="ENCTYPE_HERE">
                <div><label for="subcatgory-name">Subcatgory Name:</label>
                <input type="text" id="subcatgory-name" onChange={(e)=>setName(e.currentTarget.value)} name="name" required /></div>
                <div><label for="subcatgory-aliasname">Subcatgory Alias Name:</label>
                <input type="text" id="subcatgory-aliasname" onChange={(e)=>setAliasName(e.currentTarget.value)}  name="alias" required />
                </div><br/>
                <div>
                <label for="parent">Choose Parent:</label>
                <select name="parentName" onChange={handleSelectChange}   id="parent" required>
                <option value="none" selected disabled hidden>Select an Option</option>
              { parent.map((item,index,key)=>(<option  value={item.name} >{item.name}</option>))}
              <option value="create-new" onChange={(e)=>setParentName(select) }>Create New Category</option>
            </select>
            </div>
            <div>
            <input type="text" id="new-category"  name= 'parentName' class="new-category-input"  onChange={(e)=>setParentName(e.currentTarget.value) } placeholder="Enter new" required/>
            <label for="subcatgory-enabled">SubCatgory Enabled</label> 
            <input type="checkbox" checked={isChecked} onChange={handleCheckboxChange} />
              </div>
              <div>
                <label for="subcatgory-image">SubCatgory Image:</label>
                <input type="file" id="catgory-image" name="catgory-image" accept="image/*" onChange={(e)=>handleFileChange(e)} required/></div>
                <div id="image-preview">
                {previewImage && (
                <img id="preview" src={previewImage} alt='subcatgory'/>
                )}
                
            </div>
            <button type="button" onClick={handleSubmitCall}>Save</button>
            </form>
            <a href='/welcome' ><button >Cancel</button></a>
            </div>
            </body1>

      )
    }

    export default AddCategory;
