import * as React from 'react';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardActionArea from '@mui/material/CardActionArea';
import CardMedia from '@mui/material/CardMedia';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';

export default function Browse() {
    const [cards, setCards] = React.useState([
        {
            id: 1,
            name: "The rock",
        },{
            id: 2,
            name: "The rock",
        },{
            id: 3,
            name: "The rock",
        },{
            id: 4,
            name: "The rock",
        },{
            id: 5,
            name: "The rock",
        }
    ])
    return (
        <main>
            {/* Hero unit */}
            <Box
            sx={{
                bgcolor: 'background.paper',
                pt: 8,
                pb: 6,
            }}
            >
                <Container maxWidth="sm">
                    <Typography
                    component="h1"
                    variant="h2"
                    align="center"
                    color="text.primary"
                    gutterBottom
                    >
                    Browse videos
                    </Typography>
                    <Typography variant="h5" align="center" color="text.secondary" paragraph>
                    Browse through the list of videos and choose something you'd like to watch
                    </Typography>
                    <Stack
                    sx={{ pt: 4 }}
                    direction="row"
                    spacing={2}
                    justifyContent="center"
                    >
                        <Button variant="contained">Confetti</Button>
                        <Button variant="outlined">Explosion</Button>
                    </Stack>
                </Container>
            </Box>
            {/* End hero unit */}
            <Container sx={{ py: 8 }} maxWidth="md">
                <Grid container spacing={4}>
                    {cards.map((card) => (
                        <Grid item key={card.id} xs={12} sm={6} md={4}>
                            <Card
                            sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                            >
                                <CardMedia
                                    component="div"
                                    sx={{
                                    // 16:9
                                    pt: '56.25%',
                                    }}
                                />
                                <CardActionArea href='#'>
                                <CardContent sx={{ flexGrow: 1 }}>
                                    <Typography gutterBottom>
                                    {card.name}
                                    </Typography>
                                </CardContent>
                                </CardActionArea>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Container>
            {/* Footer has been deleted*/}
        </main>
    );
}