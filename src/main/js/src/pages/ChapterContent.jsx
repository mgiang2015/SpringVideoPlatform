import { Typography, Box, Divider } from "@mui/material";
import VideoCard from "../components/VideoCard";

export default function ChapterContent() {
    return (
        <Box>
            <Typography variant="h3">Chapter 1: The Beninging</Typography>
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
            <VideoCard videoName={"Chapter 1: The Beninging"} videoUrl={""} />
        </Box>
    )
}