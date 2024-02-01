import { Alert, Box, Button, Checkbox, Collapse, InputLabel, TextField } from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";

export default function ChapterEdit({ variant }) {
    let { courseId, chapterId } = useParams();
    let navigate = useNavigate();

    const [description, setDescription] = useState("");
    const [title, setTitle] = useState("");
    const [updated, setUpdated] = useState(false);
    const [error, setError] = useState(false);
    const [file, setFile] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/chapters/${chapterId}`)
        .then(res => {
            console.log(res.data);
            setDescription(res.data.description);
            setTitle(res.data.title);
        })
    }, [])

    const onCreateSubmit = (e) => {
        e.preventDefault();
        let formData = new FormData();
        formData.append('title', title);
        formData.append('description', description);
        if (file !== null) {
            formData.append('file', file);
        }
        formData.append('courseId', courseId);
        axios.post('http://localhost:8080/chapters', formData)
        .then((response) => {
            console.log(response.data);
            navigate(`/courses/${courseId}/edit`);
        }).catch((error) => {
            if (error.response) {
                setUpdated(false);
                setError(true);
            } 
        })
    }

    const onUpdateSubmit = (e) => {
        e.preventDefault();
        let formData = new FormData();
        formData.append('title', title);
        formData.append('description', description);
        if (file !== null) {
            formData.append('file', file);
        }
        formData.append('courseId', courseId);
        axios.put(`http://localhost:8080/chapters/${chapterId}`, formData)
        .then((response) => {
            console.log(response.data);
            setUpdated(true);
            setError(false);
        }).catch((error) => {
            if (error.response) {
                setUpdated(false);
                setError(true);
            } 
        })
    }

    const onDelete = (e) => {
        e.preventDefault();
        axios.delete(`http://localhost:8080/chapters/${chapterId}`)
        .then((response) => {
            console.log(response.data);
            navigate(`/courses/${courseId}/edit`);
        }).catch((error) => {
            if (error.response) {
                setUpdated(false);
                setError(true);
            } 
        })
    }
    
    const isEdit = variant === "edit";

    const defaultXs = 2;
    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "auto auto", gridGap: "1rem", padding: "2rem" }}>
                <TextField value={title} required onChange={e => setTitle(e.currentTarget.value)} label="Chapter title" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "flex-start" }}>
                    <input required type='file' accept=".mp4" onChange={(e) => setFile(e.target.files[0])} />
                </Box>
                <TextField value={description} required onChange={e => setDescription(e.currentTarget.value)} label="Chapter description" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "flex-start" }}>
                    <Checkbox />
                    <InputLabel>Publish Chapter</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    {
                        // Create chapter takes you back to course edit. Update just displays success
                        isEdit
                        ? <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }} onClick={onUpdateSubmit}>Update Chapter</Button>
                        : <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }} onClick={onCreateSubmit}>Create Chapter</Button>
                    }
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }} href={`/courses/${courseId}/edit`}>Back</Button>
                    {
                        // Create chapter takes you back to course edit. Update just displays success
                        isEdit
                        ? <Button variant="contained" color="error" sx={{ textTransform: "none", margin: "1em" }} onClick={onDelete}>Delete Chapter</Button>
                        : <div></div>
                    }
                </Box>
                <Collapse sx={{ gridColumn: "1 / span 2" }} in={updated}>
                    <Alert severity="success">
                        Chapter updated successfully
                    </Alert>
                </Collapse>
                <Collapse sx={{ gridColumn: "1 / span 2" }} in={error}>
                    <Alert severity="error">
                        Chapter failed to update
                    </Alert>
                </Collapse>
        </Box>
    )
}