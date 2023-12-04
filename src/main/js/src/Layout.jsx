import { Box, Container, Toolbar, Typography } from '@mui/material';
import AppBar from '@mui/material/AppBar';

export default function Layout({ children }) {
    return (
        <Box sx={{ m: 0, p: 0 }}>
            <AppBar position="relative" width="100%">
                <Container>
                    <Toolbar>
                        <Typography>Hello</Typography>
                    </Toolbar>
                </Container>
            </AppBar>
            <main>{ children }</main> 
        </Box>
    )
}