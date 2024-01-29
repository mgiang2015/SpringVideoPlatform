import { Typography, Box, Divider } from "@mui/material";
import VideoCard from "../components/VideoCard";
import InboxIcon from '@mui/icons-material/Inbox';
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

export default function ChapterContent() {
    let { courseId, chapterId } = useParams();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    useEffect(() => {
        axios.get(`http://localhost:8080/chapters/${chapterId}`)
        .then(res => {
            console.log(res.data);
            setTitle(res.data.title);
            setDescription(res.data.description);
        })
    }, [])

    const items = [
        {
            icon: <InboxIcon />,
            text: "Description"
        }, {
            icon: <InboxIcon />,
            text: "Video"
        }
    ]

    return (
        <Box sx={{ height: "100%", display: "grid", gridTemplateColumns: "1fr 5fr", gridGap: "0rem", padding: "1rem" }}>
            {/* Sidebar */}
            <Sidebar items={items} />
            <Box sx={{ height: "100%", width: "100%", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "auto" }}>
                <Typography gutterBottom variant="h3">{title}</Typography>
                <Divider />
                <Typography gutterBottom>{description}</Typography>
                <VideoCard videoName={title} videoUrl={`http://localhost:8080/chapters/${chapterId}/stream`} />
            </Box>
        </Box>
    )
}