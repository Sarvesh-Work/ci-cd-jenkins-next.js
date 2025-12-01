def codePulling(gitUrl,branch){
    echo 'Pulling code'
    git url: "${gitUrl}", branch: "${branch}"
    echo 'Done pulling code'
}