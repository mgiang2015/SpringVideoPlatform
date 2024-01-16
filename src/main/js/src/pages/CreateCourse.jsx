import { Button, TextField, Box } from "@mui/material";
import { useState } from 'react';

export default function CreateCourse() {
    const [title, setTitle] = useState("");

    const onSubmit = () => {
        // call createCourse window and jump to edit
        console.log(title);
    }

    return (
    <Box sx={{ height: "100%", width: "100%", padding: "2em"}}>
        <TextField variant="standard" label="Course name" type="text" onChange={(e) => setTitle(e.currentTarget.value)} />
        <Button variant="contained" sx={{ marginLeft: "2em", textTransform: "none" }} onClick={onSubmit}>Create Course</Button>
    </Box>
    )
}