import { Button, Container, Input, TextField, Typography, LinearProgress } from '@mui/material';
import axios from 'axios'
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

export default function Edit() {
    const [title, setTitle] = useState("");
    const [uploadProgress, setUploadProgress] = useState(0);
    let { id } = useParams();
    const [oldTitle, setOldTitle] = useState("");

    useEffect(() => {
        axios.get(`http://localhost:8080/videos/${id}`)
        .then(res => {
            console.log(res.data);
            setOldTitle(res.data.title);
        })
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8080/videos/${id}`, { title }, {
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
            <Typography gutterBottom variant='h4'>{`Update video ${oldTitle}`}</Typography>
            <form onSubmit={handleSubmit}>
                <Container sx={{ display: 'flex', flexDirection: 'column' }}>
                    <TextField required label="Updated title" placeholder='Updated title here' onChange={(e) => setTitle(e.target.value)} sx={{ m: 2 }} />
                    <Button type='submit' sx={{ m: 2 }}>
                        Update
                    </Button>
                </Container>
            </form>
            <LinearProgress variant="determinate" value={uploadProgress} />
        </Container>
    )
}