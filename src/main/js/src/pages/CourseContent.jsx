import { Avatar, Box, Button, Card, CardContent, CardMedia, CardHeader, Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Rating, Typography } from "@mui/material";
import InboxIcon from '@mui/icons-material/Inbox';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import Sidebar from "../components/Sidebar";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function CourseContent() {
    let { courseId } = useParams();
    const [description, setDescription] = useState("");
    const [chapters, setChapters] = useState([]);
    const [title, setTitle] = useState("");
    const [price, setPrice] = useState(0);

    useEffect(() => {
        axios.get(`http://localhost:8080/courses/${courseId}`)
        .then(res => {
            console.log(res.data);
            setDescription(res.data.description);
            setChapters(res.data.chapters);
            setTitle(res.data.title);
            setPrice(res.data.price);
        })
    }, [])

    const items = [
        {
            icon: <InboxIcon />,
            text: "Description"
        }, {
            icon: <InboxIcon />,
            text: "Chapters"
        }
    ]

    return (
        <Box sx={{ display: "grid", gridTemplateColumns: "1fr 5fr", gridGap: "0rem", padding: "1rem" }}>
            {/* Sidebar */}
            <Sidebar items={items} />
            {/* Course Banner / landing */}
            <Box>
                <Box>
                    <Typography gutterBottom variant="h3">{title}</Typography>
                    <Button variant="contained">Enroll for SGD{price}</Button>
                </Box>
                {/* Create component for information section: title, divider and children and margin */}
                <Box marginTop={"3em"}>
                    <Typography gutterBottom variant="h4">Course Description</Typography>
                    <Divider />
                    <Typography>{description}</Typography>
                </Box>
                <Box marginTop={"3em"}>
                    <Typography gutterBottom variant="h4">Chapters</Typography>
                    <Divider />
                    <List>
                        {
                            chapters.map(chapter => {
                                return (
                                <ListItem key={chapter.id}>
                                    <ListItemText>
                                        {chapter.title}
                                    </ListItemText>
                                </ListItem> 
                                )
                            })
                        }
                    </List>
                </Box>
            </Box>
        </Box>
    )
}