import { Box, Button, Container, Toolbar, Typography } from '@mui/material';
import AppBar from '@mui/material/AppBar';

export default function Layout({ children }) {
    return (
        <Box sx={{ width: "100vw", height: "100vh" }}>
            <AppBar position="relative" width="100%">
                <Toolbar>
                    <Button disableElevation variant='contained' sx={{ m: 1 }} href='/'>Home</Button>
                    <Button disableElevation variant='contained' sx={{ m: 1 }} href='/browse'>Browse</Button>
                    <Button disableElevation variant='contained' sx={{ m: 1 }} href='/upload'>Upload</Button>
                </Toolbar>
            </AppBar>
            <Box sx={{ height: "100%", width: "100%" }}>{ children }</Box> 
        </Box>
    )
}