import axios from 'axios';
import React from 'react'

const EditData = async(id,isChecked,aliasName ,name ,parent ,selectedFile) => {
  let result=false
    
    console.log(isChecked+' '+selectedFile +' '+name +'  '+parent +' '+selectedFile)
    if(aliasName!=''&&name!=''&&parent!=''&&selectedFile!=null){
      console.log(isChecked+' '+selectedFile +' '+name +'  '+parent +' '+selectedFile)
     // Create a FormData object to send the file to the server
     const formData = new FormData();
     console.log("post")
     formData.append('id',id);
     formData.append('parentName',parent);
     formData.append('name', name);
     formData.append('alias', aliasName);
     formData.append('enabled',isChecked);
     formData.append('file',selectedFile);
     
  // Make a POST request to your Java server with the file data
  try {
    const respons= await axios.put('http://localhost:8080/category', formData,{ 
    headers: {
    'Content-Type': 'multipart/form-data',
  },
  });
 result= respons.data;
}catch(error) {
  console.error('Error uploading data:', error);
}
}
if(aliasName!=''&&name!=''&&parent!=''&&selectedFile==null){
  console.log(isChecked+' '+selectedFile +' '+name +'  '+parent +' '+selectedFile)
 // Create a FormData object to send the file to the server
 const formData1 = new FormData();
 console.log("postphoto")
 formData1.append('id',id);
 formData1.append('parentName',parent);
 formData1.append('name', name);
 formData1.append('alias', aliasName);
 formData1.append('enabled',isChecked);

 
// Make a POST request to your Java server with the file data
try {
  const respons= await axios.put('http://localhost:8080/category/editwithoutphoto', formData1,{ 
headers: {
'Content-Type': 'multipart/form-data',
},
});
result= respons.data;
}catch(error) {
console.error('Error uploading data:', error);
}
}
return result;
}

export default EditData
