import { Avatar, Box, Button, Card, CardContent, CardMedia, CardHeader, Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Rating, Typography } from "@mui/material";
import InboxIcon from '@mui/icons-material/Inbox';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import Sidebar from "../components/Sidebar";

export default function CourseContent() {
    const items = [
        {
            icon: <InboxIcon />,
            text: "Overview"
        }, {
            icon: <InboxIcon />,
            text: "Curriculum"
        }, {
            icon: <InboxIcon />,
            text: "Reviews"
        }
    ]
    return (
        <Box>
            {/* Sidebar */}
            <Sidebar items={items} />
            {/* Course Banner / landing */}
            <Box>
                <Box>
                    <Typography variant="h3">Komputing 4 Kids</Typography>
                    <Typography>Le Minh Giang</Typography>
                    <Button variant="contained">Enroll for SGD30</Button>
                </Box>
                {/* Create component for information section: title, divider and children and margin */}
                <Box marginTop={"3em"}>
                <Typography variant="h4">Course Overview</Typography>
                <Divider />
                <Typography>Lorem ipsum dolor sit amet, 
                    consectetur adipiscing elit. 
                    Vestibulum sit amet sapien nibh. 
                    Fusce rhoncus diam vel tincidunt fermentum. 
                    Curabitur fermentum eget nibh sed gravida. 
                    Vivamus condimentum purus a nulla ultricies imperdiet. 
                    Phasellus varius, augue sed bibendum euismod, 
                    ipsum risus congue ante, eget interdum lectus lorem id massa. 
                    Etiam eu ante ex. </Typography>
                </Box>
                <Box marginTop={"3em"}>
                    <Typography variant="h4">Curriculum</Typography>
                    <Divider />
                    <List>
                        <ListItem>
                            <ListItemText>
                                Chapter 1: The beninging
                            </ListItemText>
                        </ListItem>
                        <ListItem>
                            <ListItemText>
                                Chapter 2: Exodus
                            </ListItemText>
                        </ListItem>
                    </List>
                </Box>
                <Box marginTop={"3em"}>
                    <Typography variant="h4">Reviews</Typography>
                    <Divider />
                    {/* Create review card component */}
                    <Card sx={{ maxWidth: 345 }}>
                    <CardHeader
                        avatar={
                            <Avatar>
                                R
                            </Avatar>
                        }
                        action={
                            <IconButton aria-label="settings">
                                <MoreVertIcon />
                            </IconButton>
                        }
                        title="Renaissance Remi"
                        subheader="September 14, 2016"
                    />
                        <CardContent>
                            <Rating value={5} readOnly size="medium" />
                            <Typography>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sit amet sapien nibh</Typography>
                        </CardContent>
                    </Card>
                </Box>
            </Box>
        </Box>
    )
}