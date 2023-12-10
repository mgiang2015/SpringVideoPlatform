import { Card, CardContent, CardMedia, Container, Typography } from "@mui/material";
import { useParams } from "react-router-dom";
import VideoCard from "../components/VideoCard";
import { useEffect, useState } from "react";
import axios from "axios";

export default function Watch() {
    let { id } = useParams();
    const [title, setTitle] = useState("");

    useEffect(() => {
        axios.get(`http://localhost:8080/videos/${id}`)
        .then(res => {
            console.log(res.data);
            setTitle(res.data.title);
        })
    }, [])
    
    return (
        <Container maxWidth="lg">
            <VideoCard videoName={title} videoUrl={`http://localhost:8080/videos/stream/${id}`} />
        </Container>
    )
}