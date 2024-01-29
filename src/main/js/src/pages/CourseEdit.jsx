import { Box, Checkbox, Button, InputLabel, TextField, Alert, Collapse } from "@mui/material";
import axios from "axios";
import CheckIcon from '@mui/icons-material/Check';
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export default function CourseEdit() {
    let { courseId } = useParams();
    let navigate = useNavigate();

    const [description, setDescription] = useState("");
    const [chapters, setChapters] = useState([]);
    const [title, setTitle] = useState("");
    const [price, setPrice] = useState(0);
    const [updated, setUpdated] = useState(false);
    const [error, setError] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:8080/courses/${courseId}`)
        .then(res => {
            console.log(res.data);
            setDescription(res.data.description);
            setChapters(res.data.chapters);
            setTitle(res.data.title);
            setPrice(res.data.price);
        })
    }, [])

    const onSubmit = (e) => {
        // call createCourse window and jump to edit
        console.log(title);
        e.preventDefault();
        let formData = new FormData();
        formData.append("title", title);
        formData.append("description", description);
        formData.append("price", price);
        axios.put(`http://localhost:8080/courses/${courseId}`, formData)
        .then((response) => {
            console.log(response.data); // should return course
            setUpdated(true);
            setError(false);
        }).catch((error) => {
            if (error.response) {
                setUpdated(false);
                setError(true);
            } 
        })
    }

    const onCancel = (e) => {
        navigate(`/courses/${courseId}`)
    }

    const defaultXs = 2;
    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "auto auto", gridGap: "1rem", padding: "2rem" }}>
                <TextField label="Course title" value={title} required onChange={e => setTitle(e.currentTarget.value)} />
                <Box>
                    <p>Update Chapters here</p>
                </Box>
                <TextField label="Course description" value={description} required onChange={e => setDescription(e.currentTarget.value)} />
                <Box>
                    <p>Update image here</p>
                </Box>
                <TextField label="Price SGD" type="number" value={price} required onChange={e => setPrice(e.currentTarget.value)} />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <Checkbox checked />
                    <InputLabel>Publish Course</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }} onClick={onSubmit}>Update Course</Button>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }} onClick={onCancel}>View Course</Button>
                </Box>
                <Collapse sx={{ gridColumn: "1 / span 2" }} in={updated}>
                    <Alert severity="success">
                        Course updated successfully
                    </Alert>
                </Collapse>
                <Collapse sx={{ gridColumn: "1 / span 2" }} in={error}>
                    <Alert severity="error">
                        Course failed to update
                    </Alert>
                </Collapse>
        </Box>
    )
}