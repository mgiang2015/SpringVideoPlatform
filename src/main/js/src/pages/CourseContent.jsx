import { Avatar, Box, Button, Card, CardContent, CardMedia, CardHeader, Divider, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Rating, Typography, Link } from "@mui/material";
import InboxIcon from '@mui/icons-material/Inbox';
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
        <Box sx={{ display: "grid", gridTemplateColumns: "1fr 5fr", gridGap: "1rem", padding: "1rem" }}>
            {/* Sidebar */}
            <Sidebar items={items} />
            {/* Course Banner / landing */}
            <Box>
                <Box>
                    <Typography gutterBottom variant="h3">{title}</Typography>
                    <Button sx={{ textTransform: "none", margin: "1em" }} variant="contained">Enroll for SGD{price}</Button>
                    <Button sx={{ textTransform: "none", margin: "1em" }} variant="contained" href={`/courses/${courseId}/edit`}>Edit Course</Button>
                </Box>
                {/* Create component for information section: title, divider and children and margin */}
                <Box marginTop={"3em"} sx={{ display: "flex", flexDirection: "column", alignItems: "flex-start" }}>
                    <Typography gutterBottom variant="h4" sx={{ textDecoration: "underline" }}>Course Description</Typography>
                    <Typography>{description}</Typography>
                </Box>
                <Box marginTop={"3em"} sx={{ display: "flex", flexDirection: "column", alignItems: "flex-start" }}>
                    <Typography gutterBottom variant="h4" sx={{ textDecoration: "underline" }}>Chapters</Typography>
                    <List>
                        {
                            chapters.map(chapter => {
                                return (
                                <ListItem key={chapter.id}>
                                    <ListItemText>
                                        <Link href={`/courses/${courseId}/chapters/${chapter.id}`}>{chapter.title}</Link>
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