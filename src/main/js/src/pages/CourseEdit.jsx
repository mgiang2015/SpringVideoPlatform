import { Box, Checkbox, Button, InputLabel, TextField, ToggleButton } from "@mui/material";

export default function CourseEdit() {
    const defaultXs = 2;
    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "auto auto", gridGap: "1rem", padding: "2rem" }}>
                <TextField label="Course title" />
                <Box>
                    <p>Update Chapters here</p>
                </Box>
                <TextField label="Course description" />
                <Box>
                    <p>Update image here</p>
                </Box>
                <TextField label="Price" />
                <Box sx={{ display: "flex", alignItems: "center" }}>
                    <Checkbox />
                    <InputLabel>Publish Course</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    <Button variant="contained" sx={{ textTransform: "none" }}>Update Course</Button>
                </Box>

        </Box>
    )
}