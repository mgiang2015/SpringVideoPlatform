import { Box, Checkbox, Button, InputLabel, TextField, Input } from "@mui/material";
import React, { useState } from 'react';

export default function ChapterEdit({ variant }) {
    const [file, setFile] = useState(null);
    const isEdit = variant === "edit";

    const defaultXs = 2;
    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "auto auto", gridGap: "1rem", padding: "2rem" }}>
                <TextField label="Chapter title" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <input required type='file' accept=".mp4" onChange={(e) => setFile(e.target.files[0])} />
                </Box>
                <TextField label="Chapter description" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <Checkbox />
                    <InputLabel>Publish Chapter</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    {
                        // Create chapter takes you to the chapter view. Update just displays success
                        isEdit
                        ? <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Update Chapter</Button>
                        : <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Create Chapter</Button>
                    }
                    {
                        // View chapter takes you to the chapter view. Cancel takes you back to course edit page
                        isEdit
                        ? <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>View Chapter</Button>
                        : <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Cancel</Button>
                    }
                </Box>
        </Box>
    )
}