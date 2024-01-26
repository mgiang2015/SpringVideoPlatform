import { Box, Checkbox, Button, InputLabel, TextField, Input } from "@mui/material";
import React, { useState } from 'react';

export default function ChapterEdit() {
    const [file, setFile] = useState(null);

    const defaultXs = 2;
    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "auto auto", gridGap: "1rem", padding: "2rem" }}>
                <TextField label="Chapter title" />
                <Box>
                    <Input required type='file' onChange={(e) => setFile(e.target.files[0])} />
                </Box>
                <TextField label="Chapter description" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <Checkbox />
                    <InputLabel>Publish Chapter</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Update Chapter</Button>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Cancel</Button>
                </Box>
        </Box>
    )
}