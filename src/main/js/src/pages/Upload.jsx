import { Box, Button, Input, TextField } from '@mui/material';
import React, { useState } from 'react';
import axios from 'axios'

export default function Upload() {
    const [title, setTitle] = useState("");
    const [file, setFile] = useState(null);

    const handleSubmit = () => {
        console.log(title);
        console.log(file);
        let formData = new FormData();
        formData.append('title', title);
        formData.append('file', file);
        axios.post('http://localhost:8080/videos/add', formData)
        .then((response) => {
            console.log(response);
        })
        .catch((e) => {
            console.log(e);
        })
    }

    return (
        <Box sx={{ m: 5 }}>
            <TextField onChange={(e) => setTitle(e.target.value)} />
            <Input type='file' onChange={(e) => setFile(e.target.files[0])} />
            <Button onClick={handleSubmit}>
                Upload Video
            </Button>
        </Box>
    )
}