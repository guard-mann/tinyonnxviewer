import React, { useState, useEffect, ChangeEvent } from 'react';
import axios from 'axios';


const TinyOnnxViewer: React.FC = () => {
    const [message, setMessage] = useState<string>("Loading..."); 
    const [name, setName] = useState<string>("World");
    const [imageUrl, setImageUrl] = useState<string | null>(null); 
    const [selectedFile, setSelectedFile] = useState<File | null>(null);

    const handleNameChange = (e: ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    };
    
    const handleGreetMe = async () => {
        const response = await axios.get(`/apigreeting?name=${name}`);
        setMessage(response.data.hoge);
    }

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null;
        setSelectedFile(file);
    };
    
    const handleFileUpload = async () => {
        if (!selectedFile) return;

        const formData = new FormData();
        formData.append('file', selectedFile);

        try {
            const response = await axios.post('/api/onnx/upload', formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
	    const dnsName = "tinyonnxviewer.com";
	    const fullImageUrl = `https://${dnsName}.......`;
            setImageUrl(fullImageUrl); 
        } catch (error) {
            console.error('Error uploading ONNX file:', error);
        }
    };
    

    return (
        <div>
           <hr />
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleFileUpload}>ONNX Upload</button>
            {imageUrl && <img src={imageUrl} alt="ONNX Rendered Output" style={{width: '500px', height: 'auto' }} />}
        </div>
    );
};

export default TinyOnnxViewer;
