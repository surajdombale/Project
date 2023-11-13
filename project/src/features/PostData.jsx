import React, { useState } from 'react'
import axios from 'axios';

const PostData = async (isChecked,aliasName ,name ,parent ,selectedFile) => {
   let id=0;
  console.log("post")
   
   
    console.log(isChecked+' '+selectedFile +' '+name +'  '+parent +' '+selectedFile)
    if(aliasName!=''&&name!=''&&parent!=''&&selectedFile!=null){
      console.log(isChecked+' '+selectedFile +' '+name +'  '+parent +' '+selectedFile)
     
      // Create a FormData object to send the file to the server
     const formData = new FormData();
     console.log("post")
     formData.append('parentName',parent);
     formData.append('name', name);
     formData.append('alias', aliasName);
     formData.append('enabled',isChecked);
     formData.append('file',selectedFile);
     
  // Make a POST request to your Java server with the file data
  try {
   const respons= await axios.post('http://localhost:8080/category/newsub', formData,{ 
    headers: {
    'Content-Type': 'multipart/form-data',
  },
  });
  console.log(respons.data)
 
  id=respons.data;
 

}catch(error) {
  console.error('Error uploading data:', error);
}

}else{
  window.alert('Please Fill All The Blocks')
}
return id;
//  navigate('/')
}
export default PostData
