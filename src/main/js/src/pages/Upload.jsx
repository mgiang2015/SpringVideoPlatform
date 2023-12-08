import { Button, Container, Input, TextField, Typography, LinearProgress } from '@mui/material';
import React, { useState } from 'react';
import axios from 'axios'

export default function Upload() {
    const [title, setTitle] = useState("");
    const [file, setFile] = useState(null);
    const [uploadProgress, setUploadProgress] = React.useState(0);

    const handleSubmit = (e) => {
        e.preventDefault();
        let formData = new FormData();
        formData.append('title', title);
        formData.append('file', file);
        axios.post('http://localhost:8080/videos/add', formData, {
            onUploadProgress: (progressEvent) => {
                const percentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total);
                setUploadProgress(percentCompleted);
            }
        })
        .then((response) => {
            console.log(response);
        })
        .catch((e) => {
            console.log(e);
        })
    }

    return (
        <Container maxWidth="xl" sx={{ mt: 5 }}>
            <Typography gutterBottom variant='h4'>Upload video</Typography>
            <form onSubmit={handleSubmit}>
                <Container sx={{ display: 'flex', flexDirection: 'column' }}>
                    <TextField required label="Video title" placeholder='Your title here' onChange={(e) => setTitle(e.target.value)} sx={{ m: 2 }} />
                    <Input required type='file' onChange={(e) => setFile(e.target.files[0])} sx={{ m: 2 }} />
                    <Button type='submit' sx={{ m: 2 }}>
                        Upload
                    </Button>
                </Container>
            </form>
            <LinearProgress variant="determinate" value={uploadProgress} />
        </Container>
    )
}