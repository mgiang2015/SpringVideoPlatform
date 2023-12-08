import { Card, CardContent, CardMedia, Typography } from "@mui/material";

export default function VideoCard({ videoName, videoUrl }) {
    return (
        <Card>
            <CardMedia
                component="video"
                src={videoUrl}
                controls
            />
            <CardContent>
                <Typography gutterBottom variant="h5">{videoName}</Typography>
            </CardContent>
        </Card>
    )
}