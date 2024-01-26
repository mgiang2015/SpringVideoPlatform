import { Box, Checkbox, Button, InputLabel, TextField } from "@mui/material";

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
                <TextField label="Price SGD" type="number" />
                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <Checkbox />
                    <InputLabel>Publish Course</InputLabel>
                </Box>
                <Box sx={{ gridColumn: "1 / span 2" }}>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Update Course</Button>
                    <Button variant="contained" sx={{ textTransform: "none", margin: "1em" }}>Cancel</Button>
                </Box>

        </Box>
    )
}