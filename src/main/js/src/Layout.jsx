import { Box, Button, Toolbar } from '@mui/material';
import AppBar from '@mui/material/AppBar';

export default function Layout({ children }) {
    // Layout can also take in a "variant" prop. Change toolbar / sidebar accordingly
    return (
        <Box sx={{ width: "100vw", height: "100vh" }}>
            <AppBar position="relative" width="100%">
                <Toolbar>
                    <Button disableElevation variant='contained' sx={{ m: 1, textTransform: "none" }} href='/'>Home</Button>
                    <Button disableElevation variant='contained' sx={{ m: 1, textTransform: "none" }} href='/browse'>Browse</Button>
                    <Button disableElevation variant='contained' sx={{ m: 1, textTransform: "none" }} href='/courses/create'>Create Course</Button>
                    <Button disableElevation variant='contained' sx={{ m: 1, textTransform: "none" }} href='/courses/management'>Manage Courses</Button>
                </Toolbar>
            </AppBar>
            <Box sx={{ height: "100%", width: "100%" }}>{ children }</Box> 
        </Box>
    )
}