import { Button, TextField, Box } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";

export default function CreateCourse() {
    let navigate = useNavigate();
    const [title, setTitle] = useState("");
    const [courseId, setCourseId] = useState(0);

    useEffect(() => {
        if (courseId !== 0) {
            navigate(`/courses/${courseId}/edit`)
        }
    }, [courseId])

    const onSubmit = (e) => {
        // call createCourse window and jump to edit
        console.log(title);
        e.preventDefault();
        let formData = new FormData();
        formData.append("title", title);
        axios.post("http://localhost:8080/courses", formData)
        .then((response) => {
            console.log(response.data); // should return course
            setCourseId(response.data.id);
        })
    }

    return (
    <Box sx={{ height: "100%", width: "100%", padding: "2em"}}>
        <TextField variant="standard" label="Course name" type="text" onChange={(e) => setTitle(e.currentTarget.value)} />
        <Button variant="contained" sx={{ marginLeft: "2em", textTransform: "none" }} onClick={onSubmit}>Create Course</Button>
    </Box>
    )
}