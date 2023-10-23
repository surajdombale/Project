import React from 'react'

const CSV = (jsonData) => {
    console.log(jsonData.map((item)=>(item)))
    const convertToCSV = (data) => {
        const headers = Object.keys(data[0]);
        const csvContent = [
          headers.join(','),
          ...data.map((item) => headers.map((header) => item[header]).join(',')),
        ].join('\n');
        return csvContent;
      };
     
        const csvData = convertToCSV(jsonData);
        const blob = new Blob([csvData], { type: 'text/csv' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.style.display = 'none';
        a.href = url;
        a.download = 'data.csv';
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
      
    // const csvContent = data.join('\n');
    // const blob = new Blob([csvContent], { type: 'text/csv' });
    // const url = window.URL.createObjectURL(blob);
    // const a = document.createElement('a');
    // a.style.display = 'none';
    // a.href = url;
    // a.download = 'list.csv';
    // document.body.appendChild(a);
    // a.click();
    // window.URL.revokeObjectURL(url);
    // document.body.removeChild(a);
}

export default CSV
